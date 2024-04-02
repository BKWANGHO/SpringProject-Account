'use client'

import axios from "axios"
import { useState } from "react"


const SERVER = 'http://localhost:8080'
export default function Withdraw(){

    const [totalbalance,setTotalbalance] = useState('')
    const [accountNumber,setAccountNumber] = useState('')
    const [password,setPassword] = useState('')
    const [receiverAccount,setReceiverAccount] = useState('')
    const [bank,setBank]=useState('')
    const [receiverbank,setReceiverBank]=useState('')

    const handleBalance = (e : any)=>{
        setTotalbalance(e.target.value)
    }
    const handleAccountNumber = (e : any)=>{
        setAccountNumber(e.target.value)
    }
    const handlePassword = (e : any)=>{
        setPassword(e.target.value)
    }
const handleReceiverAccount = (e : any)=>{
        setReceiverAccount(e.target.value)
    } 
    const bankChange = (e:any)=>{
      setBank(e.target.value)
    }
    const receiverbankChange = (e:any)=>{
        setBank(e.target.value)
      }
    const handleClick = ()=>{
        alert('입력완료')
        const url = `${SERVER}/transfer`
        const data = {totalbalance,accountNumber,password,receiverAccount,bank,receiverbank}
        const config = {
          headers:{
            "Cache-Control": "no-cache",
            "Content-Type": "application/json",
             Authorization: `Bearer blah ~` ,
            "Access-Control-Allow-Origin": "*",}}
        axios.post(url,data,config)
        .then(res=>{
          alert(JSON.stringify(res.data))
        })
    }

    return(<>
    <div>출금</div>
    <div>출금 금액</div>
    <input type="text" onChange={handleBalance} />
    <div>출금계좌 은행 </div>
    <input type="text" onChange={bankChange}/>
    <div>출금 계좌</div>
    <input type="text" onChange={handleAccountNumber} />
    <div>출금 계좌 비밀번호</div>
    <input type="text" onChange={handlePassword} />
    <div>입금 계좌</div>
    <input type="text" onChange={handleReceiverAccount} /><br />
    <div>입금계좌 은행 </div>
    <input type="text" onChange={receiverbankChange}/>



    <button onClick={handleClick}>완료</button>




    </>)
}