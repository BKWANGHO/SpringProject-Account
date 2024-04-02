'use client'

import axios from "axios"
import { useState } from "react"


const SERVER = 'http://localhost:8080'
export default function Deposit(){

    const [totalbalance,setTotalbalance] = useState('')
    const [accountNumber,setAccountNumber] = useState(''    )
    const [bank,setBank]=useState('')
    const handleBalance = (e : any)=>{
        setTotalbalance(e.target.value)
    }
    const handleAccountNumber = (e : any)=>{
        setAccountNumber(e.target.value)
    } 
    const bankChange = (e:any)=>{
      setBank(e.target.value)
    }
    const handleClick = ()=>{
        alert('입력완료')
        const url = `${SERVER}/deposit`
        const data = {totalbalance,accountNumber,bank}
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
    <div>입금</div>
    <div>입금 금액</div>
    <input type="text" onChange={handleBalance} />
    <div>입금 계좌</div>
    <input type="text" onChange={handleAccountNumber} />
    <div>입금계좌 은행 </div>
    <input type="text" onChange={bankChange}/>
    <button onClick={handleClick}>완료</button>




    </>)
}