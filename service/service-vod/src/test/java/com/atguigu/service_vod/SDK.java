package com.atguigu.service_vod;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

/**
 * @author ginga
 * @since 11/1/2023 下午11:53
 */
public class SDK {

    public static void main(String[] args) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai",
                                                           "LTAI5t6K96CgdMQZS4t4QJDU",
                                                           "U8CPIkyeqRaLKHmpbgNLIAFNHGnUAc");
        IAcsClient client = new DefaultAcsClient(profile);

        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setSysProtocol(ProtocolType.HTTPS);
        request.setVideoId("a05cdbc091bd71ed87836733a78e0102");

        try {
            GetPlayInfoResponse response = client.getAcsResponse(request);
            final GetPlayInfoResponse.VideoBase videoBase = response.getVideoBase();
            final List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                final String title = videoBase.getTitle();
                System.out.println(title + ": " + playInfo.getPlayURL());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }

}
