# Cordova plugin Google Sign-in
Cordova plugin support google sign in.

## Android 

### Prepare 

Before install this plugin you need consider `https://github.com/steveleetn91/cordova-firebase` and add config into `config.xml`

    <platform name="android">
        <config-file target="res/values/strings.xml" parent="/*">
            <string name="google_app_id">xxxxxxxx</string>
            <string name="server_client_id">xxxxxxxxxx</string>
        </config-file> 
    </platform>

## iOS 

### Prepare 

You need add this config into `config.xml` for iOS platform.
    <platform name="ios">
        <config-file parent="GIDClientID" target="*-Info.plist" >
            <string>xxxxxxx</string>
        </config-file>
        <config-file parent="GIDServerClientID" target="*-Info.plist" >
            <string>xxxxxxxxx</string>
        </config-file>
        <config-file parent="CFBundleURLTypes" target="*-Info.plist" >
            <array>
                <dict>
                    <key>CFBundleURLSchemes</key>
                    <array>
                    <string>xxxxxxxxxxx</string>
                    </array>
                </dict>
            </array>
        </config-file>
    </platform>

## How to install? 

    cordova plugin add https://github.com/steveleetn91/cordova-google-sign-in.git

Don't build while you just installed plugin. You shuold open your project by Xcode and add package `https://github.com/google/GoogleSignIn-iOS.git`.
I has been testing version `7.0 ~ 7.1`. Got completed you can take a built. Good luck!

## How to use? 
    
    window.googlesignin.googlesignin().then(() => {

    }).catch(() => {

    });

    document.addEventListener("google.signin.DONE",(resp) => {
        console.log(resp.token);
    })
    document.addEventListener("google.signin.FAIL",() => {
        
    })

## Issue 

If you need anything please create new issue `https://github.com/steveleetn91/cordova-firebase/issues`

## Freelancer Service (Cordova/Ionic)

If you need a freelancer for cordova project, so let's me know. I can work 16 hours / 1 day and rate is 10$/1 hour. I can speak english and IELTS scope is `6.0~7.0`.

 - Write plugin 
 - Coding App 
 - Maintain cordova/ionic app 

Contact email : hoang.le.tn91@gmail.com

Facebook: https://www.facebook.com/profile.php?id=100015561036994