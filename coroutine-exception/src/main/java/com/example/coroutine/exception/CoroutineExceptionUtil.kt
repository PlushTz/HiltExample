package com.example.coroutine.exception

/**
 * Desc:
 * @author lijt
 * Created on 2022/11/23 9:16
 * Email: lijt@eetrust.com
 */
class CoroutineExceptionUtil {
    companion object {
        fun <T> run(block: (T)): T {
            return block
        }
    }
}