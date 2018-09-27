package com.wang.xiaoyu.domain;

import java.util.ArrayList;

/**
 * Created by 小 on 2018/9/16.
 */

public class ShopData {


    /**
     * code : 200
     * data : [{"address":"重庆市大渡口区新山村街道钢花路302号君悦天下","appraisal":"5.0","brandurl":"http://img.zglcfn.com/brand_icons/aodi.png,http://img.zglcfn.com/brand_icons/bieke.png","brandurls":["http://img.zglcfn.com/brand_icons/aodi.png","http://img.zglcfn.com/brand_icons/bieke.png"],"distance":"11611049","id":97,"latitude":"29.486166","longitude":"106.488693","name":"金沙","photo":"http://lcfn-parts-images.oss-cn-shenzhen.aliyuncs.com/imgupload/1534487081767.jpg?Expires=1849847073&OSSAccessKeyId=LTAIaXKyuVedkBb8&Signature=b%2FmISyfHW6CDEahJI8k1mPB2pus%3D"},{"address":"重庆市大渡口区新山村街道文体支路88号君悦天下","appraisal":"5.0","brandurl":"http://img.zglcfn.com/brand_icons/aodi.png,http://img.zglcfn.com/brand_icons/aodi.png,http://img.zglcfn.com/brand_icons/aerfaluomiou.png","brandurls":["http://img.zglcfn.com/brand_icons/aodi.png","http://img.zglcfn.com/brand_icons/aerfaluomiou.png"],"distance":"11611037","id":99,"latitude":"29.486223","longitude":"106.488578","name":"测试","photo":"http://lcfn-parts-images.oss-cn-shenzhen.aliyuncs.com/imgupload/1534494787447.jpg?Expires=1849854778&OSSAccessKeyId=LTAIaXKyuVedkBb8&Signature=eZM%2FhCmUYhHewaUxbuFTJEUJep4%3D"},{"address":"重庆市大渡口区新山村街道钢花路81号君悦天下","appraisal":"5","brandurl":"http://img.zglcfn.com/brand_icons/aodi.png,http://img.zglcfn.com/brand_icons/aodi.png","brandurls":["http://img.zglcfn.com/brand_icons/aodi.png"],"distance":"11611030","id":103,"latitude":"29.486261","longitude":"106.48851","name":"娜娜的店","photo":"http://lcfn-parts-images.oss-cn-shenzhen.aliyuncs.com/imgupload/1535448175079.jpg?Expires=1850808165&OSSAccessKeyId=LTAIaXKyuVedkBb8&Signature=aMyjQSAcG7CwM4n8vul8iEL7IAY%3D"},{"address":"重庆市渝北区东湖南路6号6幢94-96","appraisal":"5","brandurl":"http://img.zglcfn.com/brand_icons/bieke.png","brandurls":["http://img.zglcfn.com/brand_icons/bieke.png"],"distance":"11621074","id":104,"latitude":"29.715139","longitude":"106.632064","name":"两江新区呈翔汽车维修服务中心"},{"address":"重庆市渝北区龙塔街道渝鲁大道777号鲁能星城一街区1幢1-12","appraisal":"5","brandurl":"http://img.zglcfn.com/brand_icons/biaozhi.png,http://img.zglcfn.com/brand_icons/biaozhi.png,http://img.zglcfn.com/brand_icons/changan.png,http://img.zglcfn.com/brand_icons/changcheng.png,http://img.zglcfn.com/brand_icons/benchi.png,http://img.zglcfn.com/brand_icons/aodi.png,http://img.zglcfn.com/brand_icons/bieke.png,http://img.zglcfn.com/brand_icons/bieke.png,http://img.zglcfn.com/brand_icons/bentian.png,http://img.zglcfn.com/brand_icons/bentian.png,http://img.zglcfn.com/brand_icons/bentian.png","brandurls":["http://img.zglcfn.com/brand_icons/biaozhi.png","http://img.zglcfn.com/brand_icons/changan.png","http://img.zglcfn.com/brand_icons/changcheng.png"],"distance":"11611057","id":105,"latitude":"29.486183","longitude":"106.488784","name":"重庆高鑫汽车维修有限公司"}]
     * isMore : 1
     * msg : success
     * total : 11
     */
    public ArrayList<BenData> data;


    public class BenData {
        public String id;
        public String address;
        public String appraisal;
        public String brandurl;
        public String distance;
        public String latitude;
        public String longitude;
        public String name;
        public String photo;

       /* @Override
        public String toString() {
            return "BenData="+BenData;
        }*/
    }

    @Override
    public String toString() {
        return "data="+data;
    }
}




