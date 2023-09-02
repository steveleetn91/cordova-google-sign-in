module.exports = {
    googlesignin : () => {
        return new Promise((resolve,reject) => {
            cordova.exec(() => {
                console.log("googlesignin success");
                return resolve();
              }, () => {
                console.log("googlesignin fail");
                return reject();
              }, "GoogleSignInInit", 'signin', []);
        })
    }
}