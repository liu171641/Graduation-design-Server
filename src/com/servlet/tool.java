
package com.servlet;
/** 
 * @����:��� 
 * @����:1716413010@qq.com 
 * @�汾: v1.0
 * @����:2020��11��16�� ����5:36:22
 * @����:
 */

public class tool {
    public String removeGang(String xx) {
        xx = xx.replaceAll("\\\\", "/");    //Ϊʲô��ôд�����Կ���UploadServlet.java �ϴ�ͼƬ�Ĵ洢·����xxx\xxxx\xxxxx\xxx\xx   Ϊ�˽���ͼƬ�ӷ������ж�ȡ��������Ҫͨ��url��ַ�����ʣ�Ȼ�조\���ǷǷ�url���룬��Ҫ��Ϊ��/"
        System.out.println(xx);
        return xx;
    }
}
