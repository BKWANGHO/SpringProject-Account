'use client'

import axios from "axios"
import { useRouter } from "next/navigation"
import { useState } from "react"

const SERVER = 'http://localhost:8080'
export default function Service() {
    
    const router = useRouter();
    
    const handleDeposit = () => {
        router.push("/deposit")
    }
    const handleWithdraw = () => {
        router.push("/withdraw")
    }
    const handleAccountTransfer = () => {
        router.push("/accountTransfer")
    }
    const handleHistory = () => {
        router.push("/history")
    }
    return (<>
        <div>서비스를 선택하세요</div>
        <button onClick={handleDeposit}>입금</button>
        <button onClick={handleWithdraw}>출금</button>
        <button onClick={handleAccountTransfer}>송금</button>
        <button onClick={handleHistory}>거래내역</button>




    </>)
}