package com.hzb.utils.net.runFun.bean;

import com.hzb.utils.net.Utils.BaseResponse;

/**
 * http://pndatsn5v.bkt.clouddn.com/rxjava_retrofit.txt
 */
public class Demo extends BaseResponse {

    /**
     * data : {"userPhoto":"","signature":"wertyhjhgfdsadfdghjgfwefgbn","wifiName":"fsadfasdfasdqweqwe","penMac":"werwqerqw","clazzId":"29477599276482560","userAccount":"29752888107757568","rongTokenId":"","wifiPwd":"fsadfasdfqweqwe","penType":"2","id":"29752888107757568","userName":"sdfsadf","clazzName":"sdfasdfsa"}
     */

    private DataBean data;

    @Override
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String userPhoto;
        private String signature;


        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "userPhoto='" + userPhoto + '\'' +
                    ", signature='" + signature + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Demo{" +
                "data=" + data +
                '}';
    }
}
