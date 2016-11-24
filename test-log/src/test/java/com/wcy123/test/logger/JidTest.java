package com.wcy123.test.logger;

import org.junit.Assert;
import org.junit.Test;

public class JidTest {
    public String[] getOrgAppName(String jid) {
        final int i1 = jid.indexOf('#');
        final int i2 = jid.indexOf('_');
        final int i3 = jid.indexOf('@');
        if (!(i1 != -1 && i2 != -1 && i3 != -1 && i2 > i1 && i3 > i2)) {
            throw new IllegalArgumentException("not a valid jid:" + jid);
        }
        return new String[] {
                jid.substring(0, i1),
                jid.substring(i1 + 1, i2),
                jid.substring(i2 + 1, i3),
                jid.substring(i3 + 1)
        };
    }

    @Test(expected = IllegalArgumentException.class)
    public void main1() throws Exception {
        getOrgAppName("abc");
    }

    @Test
    public void main2() throws Exception {
        Assert.assertArrayEquals(
                new String[] {"appkey", "abc", "bc", "easemob.com"},
                getOrgAppName("appkey#abc_bc@easemob.com"));
    }

    @Test
    public void main3() throws Exception {
        Assert.assertArrayEquals(
                new String[] {"appkey", "ab", "c_bc", "easemob.com"},
                getOrgAppName("appkey#ab_c_bc@easemob.com"));
    }

    @Test
    public void main4() throws Exception {
        Assert.assertArrayEquals(
                new String[] {"appkey", "a", "", "easemob.com"},
                getOrgAppName("appkey#a_@easemob.com"));
    }
}
