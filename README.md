# Edit: Changes in API level 23 and newer make impossible for normal application to modify the autorotate setting

The application presented in this blog uses the `android.permission.WRITE_SETTINGS` that, starting from Android v6.0 Marshmallow (API level 23), requires the application to be *system or signed* to be able to run without user intervention.

There's a workaround that can be used targeting API level 22. In this way the application can still use the old permission model and it can write into the settings.

However, [because Google already announced that from August 2018 new application will need to target API level 26 to be accepted into the Play Store](https://android-developers.googleblog.com/2017/12/improving-app-security-and-performance.html), this is a short lived solution. It's only a viable solution if your application is never going to be included in the play store.

Anyway, [I've updated the code on github with this change](https://github.com/pfmaggi/autorotate) and uploaded [a new apk here](https://github.com/pfmaggi/autorotate/releases/download/v1.0.1/autorotate.apk).

**To handle correctly this permission in a normal application, user intervention is required.**

The application should use the `Settings.ACTION_MANAGE_WRITE_SETTINGS` intent with something like:

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (!Settings.System.canWrite(getApplicationContext())) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            bDoIt = false;
        }
    }
    
You can read [here a full description of the usecase for this application](http://pietromaggi.com/2016/06/07/managing-display-autorotation-from-stagenow/).

~Pietro
