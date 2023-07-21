

package net.github.dn12.network.util

data class MyNetworkRequestResult<out T>(
    val callResult: MyNetworkCallResult,
    val data: T?
) {

    companion object {

        fun <T> success(
            data: T? = null
        ): MyNetworkRequestResult<T> =
            MyNetworkRequestResult(
                MyNetworkCallResult.SUCCESS_REMOTE,
                data
            )

        fun <T> error(
            callResultError: MyNetworkCallResult
        ): MyNetworkRequestResult<T> =
            MyNetworkRequestResult(
                callResultError,
                null
            )
    }
}
