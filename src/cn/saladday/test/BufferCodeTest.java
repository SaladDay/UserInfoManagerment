package cn.saladday.test;

import cn.saladday.utils.BufferCodeUtils;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferCodeTest {

    @Test
    public void test01(){
        String code = null;
        System.out.println(BufferCodeUtils.getSecurityCode());
        System.out.println(BufferCodeUtils.getSecurityCode(4, BufferCodeUtils.SecurityCodeLevel.Medium, false));

    }
    @Test
    public void test02() throws IOException {

    }
}
