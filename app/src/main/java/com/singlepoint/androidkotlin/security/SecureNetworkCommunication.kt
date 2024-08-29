package com.singlepoint.androidkotlin.security

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient



/***
 * Secure Network communication to prevent main in the middle attacks
 * ***/



/* This below client will be accept the response from the certified server - will not accept if it comes from other server which is not certififed */

val client = OkHttpClient.Builder()
    .certificatePinner(
        CertificatePinner.Builder()
            .add("yourdomain.com", "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
            .build()
    )
    .build()

/***
 * Sending API token for every request to avoid fake api request to the server
 * This token will be passing via API client
 * ***/

//Example
//val tokenResponse = authorizationService.performTokenRequest(
//    tokenRequest,
//    ClientSecretPost(clientSecret)
//)



/*Secure Code Practises */
//Obfuscate Code
//Use ProGuard or R8 to obfuscate your code.
//This below code is belong to gradle file
//buildTypes {
//    release {
//        minifyEnabled true
//        proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//    }
//}



/****
 *
 * Somwe other secure methods
 * 1. Biometric Authentication
 * 2. Secure User Input - data validation with Regu;ar expression
 * 3.  Configure WebView to enhance security.
 * 4. Secure Permissions - Minimize the permission and ask permission when needed
 * */


/*webView.settings.javaScriptEnabled = false
webView.settings.allowFileAccess = false
webView.webViewClient = object : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        if (Uri.parse(url).host == "yourdomain.com") {
            return false
        }
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            startActivity(this)
        }
        return true
    }
}*/


/** To check the device integrity
 * Device integrity is the quality of a device's core software and firmware being correct and unmodified.
 * It's important because it helps prevent a device from booting into a compromised state.
 *  simple words rooting the device and changed the os framework
 *
 *
 *
 *  Use Security Libraries
 * Use security libraries like Google's SafetyNet to verify the device's integrity.
 * */



