
package com.servlet;
/** 
 * @作者:刘宇航 
 * @邮箱:1716413010@qq.com 
 * @版本: v1.0
 * @日期:2020年11月16日 下午5:36:22
 * @作用:
 */

public class tool {
    public String removeGang(String xx) {
        xx = xx.replaceAll("\\\\", "/");    //为什么这么写？可以看下UploadServlet.java 上传图片的存储路径：xxx\xxxx\xxxxx\xxx\xx   为了将该图片从服务器中读取出来，需要通过url地址来访问，然鹅“\“是非法url编码，需要变为”/"
        System.out.println(xx);
        return xx;
    }
}
