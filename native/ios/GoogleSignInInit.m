#import <Cordova/CDV.h>
#import "GoogleSignInInit.h"
@import GoogleSignIn;
@implementation GoogleSignInInit 
    - (void)signin:(CDVInvokedUrlCommand *)command {
        CDVPluginResult *pluginResult;
            NSString *callbackId = command.callbackId;
        [GIDSignIn.sharedInstance signInWithPresentingViewController:self.viewController
                                                      completion:^(GIDSignInResult * _Nullable signInResult,
                                                                   NSError * _Nullable error) {
              if (error) { return; }
              if (signInResult == nil) { return; }

              [signInResult.user refreshTokensIfNeededWithCompletion:^(GIDGoogleUser * _Nullable user,
                                                                       NSError * _Nullable error) {
                  if (error) {
                      [self fireEvent:@"" event:@"google.signin.FAIL" withData:nil];
                      
                      return; }
                  if (user == nil) { return; }
                  NSString *token = user.idToken.tokenString;
                  NSString *responseFormat = [NSString stringWithFormat:@"{ 'token': '%@' }",token];
                  NSLog(@"%@", responseFormat);
                  [self fireEvent:@"" event:@"google.signin.DONE" withData:responseFormat];
              }];
        }];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:callbackId];
        return;
    }
    - (void) fireEvent:(NSString *)obj event:(NSString *)eventName withData:(NSString *)jsonStr {
        NSString* js;
        if(obj && [obj isEqualToString:@"window"]) {
            js = [NSString stringWithFormat:@"var evt=document.createEvent(\"UIEvents\");evt.initUIEvent(\"%@\",true,false,window,0);window.dispatchEvent(evt);", eventName];
        } else if(jsonStr && [jsonStr length]>0) {
            js = [NSString stringWithFormat:@"javascript:cordova.fireDocumentEvent('%@',%@);", eventName, jsonStr];
        } else {
            js = [NSString stringWithFormat:@"javascript:cordova.fireDocumentEvent('%@');", eventName];
        }
        [self.commandDelegate evalJs:js];
    }
@end
