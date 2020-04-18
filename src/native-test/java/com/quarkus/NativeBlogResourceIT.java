package com.quarkus;

import com.quarkus.resource.BlogResourceTest;
import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeBlogResourceIT extends BlogResourceTest {

    // Execute the same tests but in native mode.
}