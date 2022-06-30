# GithubV3MvvmAndroid
<b>Design an Android App to display All Closed Pull Requests from Github public repository of your own code.</b>

Design Pattern: MVVM<br>

Api that is being called Internally: https://api.github.com/repos/dhruvkaushal11/GithubV3MvvmAndroid/pulls?state=closed<br>

Network Library Used:  <b>Retrofit </b><br><br>
Image Laoding Library: <b>Glide</b><br><br>


Variables can be changed directly from App Level build.gradle

        debug {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            buildConfigField "String", "BASE_URL", "\"https://api.github.com\""
            buildConfigField "String", "REPO", "\"GithubV3MvvmAndroid\""
            buildConfigField "String", "OWNER", "\"dhruvkaushal11\""
        }
    


<img src='https://sagemaker-ap-south-1-718161324995.s3.ap-south-1.amazonaws.com/ScreenShot.png' width="300px">
