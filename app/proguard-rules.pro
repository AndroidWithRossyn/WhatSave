# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/hemanths/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*
-dontwarn javax.annotation.**

# RetroFit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items). 
-keep,allowobfuscation,allowshrinking interface retrofit2.Call 
-keep,allowobfuscation,allowshrinking class retrofit2.Response 

# With R8 full mode generic signatures are stripped for classes that are not 
# kept. Suspend functions are wrapped in continuations where the type argument 
# is used. 
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation 

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep interface com.squareup.okhttp3.** { *; }
-dontwarn com.squareup.okhttp3.**

#-dontwarn
#-ignorewarnings

#Jaudiotagger
-dontwarn org.jaudiotagger.**
-dontwarn org.jcodec.**
-keep class org.jaudiotagger.** { *; }
-keep class org.jcodec.** { *; }

#Gson
-keep class com.google.gson.reflect.TypeToken
-keep class * extends com.google.gson.reflect.TypeToken
-keep public class * implements java.lang.reflect.Type

-keepclassmembers enum * { *; }
-keepattributes *Annotation*, Signature, Exception
-keepnames class androidx.navigation.fragment.NavHostFragment
-keep class * extends androidx.fragment.app.Fragment{}
-keepnames class * extends android.os.Parcelable
-keepnames class * extends java.io.Serializable
-keep class com.simplified.wsstatussaver.mediator.WAClient { *; }
-keep class com.simplified.wsstatussaver.model.** { *; }
-keep class com.simplified.wsstatussaver.update.** { *; }
-keep class com.simplified.wsstatussaver.service.** { *; }
-keep class com.google.android.material.bottomsheet.** { *; }