package com.quarkus;

import com.quarkus.integration.BlogResourceITest;
import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeBlogResourceIT extends BlogResourceITest {

    // Execute the same tests but in native mode.
}