package org.wcy123;

import org.junit.Test;
import org.wcy123.api.DataTypesTest;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.ListValue;
import com.google.protobuf.Timestamp;
import com.google.protobuf.Value;
import com.google.protobuf.util.JsonFormat;

import com.googlecode.protobuf.format.JsonJacksonFormat;

public class JsonTest {
    @Test
    public void main1() throws Exception {
        final long currentTimeMillis = System.currentTimeMillis();
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
                .setAny(Any.pack(DataTypesTest.Point.newBuilder().setX(10).setY(20).build()))
                .putAMap("m1", ListValue.newBuilder()
                        .addValues(Value.newBuilder().setStringValue("h1").build())
                        .addValues(Value.newBuilder().setStringValue("h2").build())
                        .build())
                .putAMap("m2", ListValue.newBuilder().build())
                .setTimestamp(Timestamp.newBuilder()
                        .setSeconds(currentTimeMillis / 1000)
                        .setNanos((int) (currentTimeMillis % 1000))
                        .build())
                .build();
        final JsonJacksonFormat format = new JsonJacksonFormat();
        System.out.println(format.printToString(root));
        final JsonFormat.Printer printer = JsonFormat.printer().usingTypeRegistry(
                JsonFormat.TypeRegistry.newBuilder()
                        .add(DataTypesTest.Point.getDescriptor())
                        .build());
        System.out.println(printer.print(root));
    }
}
