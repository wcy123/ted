package com.wcy123.parsec.stage6;

import org.junit.Test;

public class VarTest {
    @Test
    public void main() throws Exception {
        vf(1, 2, 3);
    }

    private void vf(int... xs) {
        for (int i = 0; i < xs.length; i++) {
            System.out.println("xs[" + i + "]:" + xs[i]);
        }
    }
}
