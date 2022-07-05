package com.example.model

import java.io.Serializable

data class LoginRequest
    (val message:String?=null,
     val code:String?=null,
     val frontendmessage:String?=null,
     val token:String?=null,
     val mpinId:String?=null,
     val otpId:String?=null,
     val actionRequired:String?=null,
     val email:String?=null,
     val password:String?=null,
     val platform:String="android"):Serializable