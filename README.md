# Cordova plugin Google Sign-in
Cordova plugin support google sign in.

## Prepare 

Before install this plugin you need consider `https://github.com/steveleetn91/cordova-firebase`

## How to install? 

    cordova plugin add https://github.com/steveleetn91/cordova-google-sign-in.git

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