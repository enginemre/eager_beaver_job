# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.engin.eagerbeaver.data.auth.mapper.* { *; }
-keep class com.engin.eagerbeaver.data.main.mapper.* { *; }
-keep class com.engin.eagerbeaver.data.main.remote.dto.* { *; }
-keep class com.engin.eagerbeaver.common.domain.model.* { *; }
-keep class com.engin.eagerbeaver.data.main.remote.dto.* { *; }
-keep class com.engin.eagerbeaver.data.auth.remote.dto.* { *; }
-keep class com.engin.eagerbeaver.domain.auth.model.* { *; }
-keep class com.engin.eagerbeaver.domain.main.model.* { *; }
-dontwarn okio.**
-dontwarn java.lang.invoke.*
-keep class retrofit.** { *; }
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keepclasseswithmembers interface * {
    @retrofit.http.* <methods>;
}
-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}

-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class javax.inject.** { *; }
 -keep class com.yourproject.YourClass**
-keep class com.engin.** { *; }
-keep class com.google.** { *; }
-keep class com.github.** { *; }
-keep class org.apache.** { *; }
-keep class com.android.** { *; }
-keep class junit.** { *; }