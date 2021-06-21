# My Application-START

# retrace setting.
-keepattributes LineNumberTable,SourceFile

# remove log
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
}
-keep class com.example.androidapptemplate.R$* { *; }
-keep class com.example.androidapptemplate.data.R$* { *; }

# TODO jsonパースで使うobjectやプリファレンスのモデルなどは暗号化対象外にする
#noinspection ShrinkerUnresolvedReference
-keep class com.example.androidapptemplate.domain.features.algorithm.model.** { *; }
-keep class com.example.androidapptemplate.domain.features.login.model.** { *; }
-keep class com.example.androidapptemplate.domain.features.webapi.trivia.model.** { *; }
-keep class com.example.androidapptemplate.domain.features.webapi.unsplash.model.** { *; }
-keep class com.example.androidapptemplate.data.features.unsplash.api.response.** { *; }

# enum
# https://stackoverflow.com/questions/46102906/kotlin-proguard-rule-for-enum
-keepclassmembers enum * {
    public *;
}

# serializable
# https://www.guardsquare.com/en/products/proguard/manual/examples#serializable
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


# https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-parceler.pro
# Parceler rules
# Source: https://github.com/johncarl81/parceler#configuring-proguard
-keep class * implements android.os.Parcelable {
#noinspection ShrinkerUnresolvedReference
    public static final android.os.Parcelable$Creator *;
}
-keep class org.parceler.Parceler$$Parcels
# My Application-END


# Android X-START
#noinspection ShrinkerUnresolvedReference
-dontwarn androidx.**
-keep class androidx.** { *; }
-keep interface androidx.** { *; }
# Android X-END

# Glide-START
# https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-glide.pro

# Glide specific rules #
# https://github.com/bumptech/glide

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
# Glide-END

# OkHttp3-START
# https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-square-okhttp3.pro
#noinspection ShrinkerUnresolvedReference
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
# OkHttp3-END

# retrofit2-START
# AS 4.0で $のところワーニング出てるが、気にしなくてOK
# https://github.com/square/retrofit/issues/3234
# https://github.com/square/retrofit/issues/3234#issuecomment-555829468
# https://github.com/krschultz/android-proguard-snippets/blob/master/libraries/proguard-square-retrofit2.pro
#noinspection ShrinkerUnresolvedReference
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
# retrofit2-END

# Moshi-START
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
# Moshi-END
