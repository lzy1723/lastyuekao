package com.example.lastyuekao.Bean;

import java.util.List;

public class JavaBean {
    /**
     * code : 0
     * message : 成功
     * data : {"list":[{"name":"中国联通话费10元","stockCount":200,"sellCount":15,"price":10,"pic":"http://bpic.588ku.com/element_origin_min_pic/16/08/09/1357a970e9d51c9.jpg"},{"name":"中国联通话费20元","stockCount":150,"sellCount":10,"price":20,"pic":"http://bpic.588ku.com/element_origin_min_pic/16/08/09/1357a970e9d51c9.jpg"},{"name":"中国电信50元","stockCount":50,"sellCount":40,"price":50,"pic":"http://img1.imgtn.bdimg.com/it/u=3465488602,253756323&fm=26&gp=0.jpg"},{"name":"中国移动100元","stockCount":0,"sellCount":40,"price":100,"pic":"http://img0.pconline.com.cn/pconline/1309/27/3550817_1111-1_thumb.jpg"}],"service":2}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"name":"中国联通话费10元","stockCount":200,"sellCount":15,"price":10,"pic":"http://bpic.588ku.com/element_origin_min_pic/16/08/09/1357a970e9d51c9.jpg"},{"name":"中国联通话费20元","stockCount":150,"sellCount":10,"price":20,"pic":"http://bpic.588ku.com/element_origin_min_pic/16/08/09/1357a970e9d51c9.jpg"},{"name":"中国电信50元","stockCount":50,"sellCount":40,"price":50,"pic":"http://img1.imgtn.bdimg.com/it/u=3465488602,253756323&fm=26&gp=0.jpg"},{"name":"中国移动100元","stockCount":0,"sellCount":40,"price":100,"pic":"http://img0.pconline.com.cn/pconline/1309/27/3550817_1111-1_thumb.jpg"}]
         * service : 2
         */

        private int service;
        private List<ListBean> list;

        public int getService() {
            return service;
        }

        public void setService(int service) {
            this.service = service;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * name : 中国联通话费10元
             * stockCount : 200
             * sellCount : 15
             * price : 10
             * pic : http://bpic.588ku.com/element_origin_min_pic/16/08/09/1357a970e9d51c9.jpg
             */
            private int servicePrice;
            private String name;
            private int stockCount;
            private int sellCount;
            private int price;
            private String pic;
            private boolean ischeck = false;

            public boolean isIscheck() {
                return ischeck;
            }

            public void setIscheck(boolean ischeck) {
                this.ischeck = ischeck;
            }

            public int getServicePrice() {
                return servicePrice;
            }

            public void setServicePrice(int servicePrice) {
                this.servicePrice = servicePrice;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getStockCount() {
                return stockCount;
            }

            public void setStockCount(int stockCount) {
                this.stockCount = stockCount;
            }

            public int getSellCount() {
                return sellCount;
            }

            public void setSellCount(int sellCount) {
                this.sellCount = sellCount;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
