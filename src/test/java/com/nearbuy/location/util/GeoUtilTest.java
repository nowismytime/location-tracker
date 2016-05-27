package com.nearbuy.location.util;

import com.nearbuy.location.dao.TestUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tushar on 27/05/16.
 */
public class GeoUtilTest {

    @Test
    public void test(){
        Long timeStart = AppUtil.currentTime();
        for(int i = 1;i<=100;i++)
            GeoUtil.isInside(
                    TestUtil.getTestPolygon2Center(), TestUtil.getTestPolygon2());
        Long timeEnd = AppUtil.currentTime();
        System.out.println("time : " + (timeEnd-timeStart));
        Assert.assertTrue(GeoUtil.isInside(
                TestUtil.getTestPolygon2Center(), TestUtil.getTestPolygon2())
        );
        Assert.assertFalse(GeoUtil.isInside(
                TestUtil.getTestPolygon2Center(), TestUtil.getTestPolygon1())
        );
    }

}
