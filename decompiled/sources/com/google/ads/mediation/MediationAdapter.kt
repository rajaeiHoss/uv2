package com.google.ads.mediation

@java.lang.Deprecated
interface MediationAdapter<ADDITIONAL_PARAMETERS : Any, SERVER_PARAMETERS : MediationServerParameters> {
    fun destroy()

    val additionalParametersType: Class<ADDITIONAL_PARAMETERS>

    val serverParametersType: Class<SERVER_PARAMETERS>
}
