package com.example.prjt

import android.os.Build
import android.os.StrictMode
import java.io.BufferedReader

import java.io.IOException
import java.io.InputStreamReader

import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.*

object Api {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>): Array<String> {
        val urlBuilder =
            StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson") /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=Z7Xxg4zfaCuZSRqGg5FUJ%2F75l%2F7q7140enZ%2FGmbnyFjcCS3q7VWprT8IFHekqXKyU3yvjxt7a7Mfh8QfdufVGg%3D%3D") /*Service Key*/

        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")) /*페이지번호*/
        urlBuilder.append(
            "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(
                "10",
                "UTF-8"
            )
        ) /*한 페이지 결과 수*/
        urlBuilder.append(
            "&" + URLEncoder.encode("startCreateDt", "UTF-8") + "=" + URLEncoder.encode(
                "20200310",
                "UTF-8"
            )
        ) /*검색할 생성일 범위의 시작*/
        urlBuilder.append(
            "&" + URLEncoder.encode("endCreateDt", "UTF-8") + "=" + URLEncoder.encode(
                "20200315",
                "UTF-8"
            )
        ) /*검색할 생성일 범위의 종료*/
        val url = URL(urlBuilder.toString())
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Content-type", "application/json")
        val rd= BufferedReader(InputStreamReader(conn.inputStream))

        val sb = StringBuilder()
        var line: String?
        var arr=Array(13) { "" }
        line=rd.readLine().toString()
        arr[0]=line.split("<accDefRate>")[1].split("<")[0]
        arr[1]=line.split("<accExamCompCnt>")[1].split("<")[0]
        arr[2]=line.split("<careCnt>")[1].split("<")[0]
        arr[3]=line.split("<clearCnt>")[1].split("<")[0]
        arr[4]=line.split("<createDt>")[1].split("<")[0]
        arr[5]=line.split("<deathCnt>")[1].split("<")[0]
        arr[6]=line.split("<decideCnt>")[1].split("<")[0]
        arr[7]=line.split("<examCnt>")[1].split("<")[0]
        arr[8]=line.split("<resutlNegCnt>")[1].split("<")[0]
        arr[9]=line.split("<seq>")[1].split("<")[0]
        arr[10]=line.split("<stateDt>")[1].split("<")[0]
        arr[11]=line.split("<stateTime>")[1].split("<")[0]
        arr[12]=line.split("<updateDt>")[1].split("<")[0]
        rd.close()
        conn.disconnect()
        return arr
    }
}