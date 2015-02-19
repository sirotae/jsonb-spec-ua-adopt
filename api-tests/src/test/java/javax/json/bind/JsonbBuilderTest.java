package javax.json.bind;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.json.bind.spi.JsonbProvider;


/**
 * Created by Olena_Syrota on 2/11/2015.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(JsonbProvider.class)
public class JsonbBuilderTest {

    @Test
    public void newBuilder_ProviderNameGiven_MockTest() {

        JsonbProvider provider = Mockito.mock(JsonbProvider.class);

        PowerMockito.mockStatic(JsonbProvider.class);
        Mockito.when(JsonbProvider.provider("SomeName")).thenReturn(provider);

        // error of powermock - powermock can not mpck with static method invocation from INTERFACE
        // Error Reason: javassist.bytecode.InterfaceMethodrefInfo cannot be cast to javassist.bytecode.MethodrefInfo
        JsonbBuilder.newBuilder("SomeName");


        Mockito.verify(provider).create();

        PowerMockito.verifyStatic();
        JsonbProvider.provider("SomeName");


     }


}
