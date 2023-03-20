package com.atguigu.order.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.common_utils.ErrorInfo;
import com.atguigu.common_utils.Result;
import com.atguigu.common_utils.sso.JWTUtils;
import com.atguigu.order.client.CourseFrontClient;
import com.atguigu.order.client.UcenterMemberClient;
import com.atguigu.order.converter.OrderConverter;
import com.atguigu.order.mapper.OrderMapper;
import com.atguigu.order.service.OrderService;
import com.atguigu.service_base.exceptionhandler.exception.GuliException;
import com.atguigu.service_pojo.pojo.Order;
import com.atguigu.service_pojo.pojo.UcenterMember;
import com.atguigu.service_pojo.vo.CourseVo;
import com.atguigu.service_pojo.vo.param.QueryOrderParam;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author ginga
 * @since 17/1/2023 下午10:32
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {

    private final CourseFrontClient courseFrontClient;

    private final UcenterMemberClient memberClient;

    private final OrderConverter orderConverter;

    @Override
    public Result add(String courseId, String token) {
        // 1 获取用户信息
        final Optional<String> memberIdOpt = JWTUtils.getMemberIdByToken(token);
        if (!memberIdOpt.isPresent()) {
            return Result.fail(ErrorInfo.MEMBER_NOT_EXISTS);
        }
        final String memberId = memberIdOpt.get();
        final Optional<UcenterMember> memberOpt = memberClient.getMemberInfo(memberId);
        if (!memberOpt.isPresent()) {
            return Result.fail(ErrorInfo.MEMBER_NOT_EXISTS);
        }

        // 1.1 如果存在则返回
        Optional<Order> existedOrderOpt = this.lambdaQuery()
                                              .select(Order::getOrderNo, Order::getStatus)
                                              .eq(Order::getCourseId, courseId)
                                              .eq(Order::getMemberId, memberId)
                                              .oneOpt();

        if (existedOrderOpt.isPresent()) {
            final Order existedOrder = existedOrderOpt.get();
            if (existedOrder.getStatus() == 0) { // 订单未支付
                return Result.success(existedOrder.getOrderNo());
            } else { // 订单已支付
                return Result.fail(ErrorInfo.ORDER_ALREADY_PAID);
            }
        }

        // 2 获取课程信息
        final Optional<CourseVo> courseOpt = courseFrontClient.order(courseId);
        if (!courseOpt.isPresent()) {
            return Result.fail(ErrorInfo.COURSE_NOT_EXISTS);
        }
        final CourseVo courseVo = courseOpt.get();

        // 3 生成订单
        // 3.1 Snowflake 算法 生成 全局唯一订单号
        String orderNo = IdUtil.getSnowflakeNextIdStr();
        // 3.2 获取教师姓名
        final String teacherName = courseVo.getTeacherName();
        // 3.3 生成order对象
        final Order order = orderConverter.toOrder(courseVo, memberOpt.get());
        order.setOrderNo(orderNo);
        order.setTeacherName(teacherName);

        // 4 保存并返回, 加锁, 一个用户只能对一个课程生成一个订单
        synchronized ((memberId + courseId).intern()) {

            existedOrderOpt = this.lambdaQuery()
                                  .eq(Order::getCourseId, courseId)
                                  .eq(Order::getMemberId, memberId)
                                  .oneOpt();

            if (existedOrderOpt.isPresent()) {
                final Order existedOrder = existedOrderOpt.get();
                return Result.success(existedOrder.getOrderNo());
            }

            return this.save(order) ? Result.success(orderNo) : Result.fail(ErrorInfo.ADD_ORDER_ERROR);
        }
    }

    @Override
    public Result detail(String orderNo) {

        if (StringUtils.isBlank(orderNo)) {
            return Result.fail(ErrorInfo.PARAMS_ARE_BLANK);
        }

        final Optional<Order> orderOpt = this.lambdaQuery()
                                             .eq(Order::getOrderNo, orderNo)
                                             .oneOpt();

        return orderOpt.map(Result::success)
                       .orElseGet(() -> Result.fail(ErrorInfo.ORDER_NOT_EXISTS));

    }

    @Override
    public Boolean getOrderStatus(QueryOrderParam queryOrderParam) {
        Assert.notNull(queryOrderParam, "queryOrderParam is null");
        final String courseId = queryOrderParam.getCourseId();
        final String memberId = queryOrderParam.getMemberId();
        if (StringUtils.isAnyBlank(courseId, memberId)) {
            throw new GuliException(ErrorInfo.PARAMS_ARE_BLANK);
        }

        // 根据课程id和会员id查询订单
        final Long count = this.lambdaQuery()
                               .eq(Order::getCourseId, courseId)
                               .eq(Order::getMemberId, memberId)
                               .eq(Order::getStatus, 1)
                               .count();

        return count > 0;
    }
}
