package org.wcy123;

import org.junit.Test;
import org.wcy123.api.DataTypesTest;

import com.google.protobuf.ByteString;

import com.googlecode.protobuf.format.JsonFormat;

public class JsonTest {
    @Test
    public void main1() throws Exception {
        final DataTypesTest.Root root = DataTypesTest.Root.newBuilder()
                .setObj1(DataTypesTest.Obj1.newBuilder()
                        .setName("YourName")
                        .build())
                .setBase64(ByteString.copyFrom("abc123!?$*&()'-=@~", "UTF-8"))
                .setAnEnum(DataTypesTest.Root.AnEnum.FOO_BAR)
                .setABoolean(true)
                .setAString("hello world")
                .setAInt32(-32)
                .setAUint32(32)
                .setAFixed(64)
                .setAInt64(-64)
                .setAUint64(64)
                .setAFixed64(128)
                .setAFloat(1.0f)
                .setADouble(2.0)
                .setEitherString("Now It is a String, not an integer")
                .build();
        final JsonFormat format = new JsonFormat();
        System.out.println(format.printToString(root));

    }
}
