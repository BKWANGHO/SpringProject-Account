'use client'

import { useState } from "react"
import axios from 'axios'
import Link from "next/link"
import { useRouter } from "next/navigation"

const SERVER = 'http://localhost:8080'
export default function Home() {
  const [username,setUsername] = useState('')
  const [password,setPassword] = useState('')
  const [name,setName] = useState('')
  const [accountNumber,setAccountNumber] = useState('')
  const [bank,setBank]=useState('')
  
  const usernameChange = (e:any) => {
    setUsername(e.target.value)
  }
  
  const passwordChange = (e:any)=>{
    setPassword(e.target.value)
  }
  const nameChange = (e:any)=>{
    setName(e.target.value)
  }
  const accountNumberChange = (e:any)=>{
    setAccountNumber(e.target.value)
  }
  
  const bankChange = (e:any)=>{
    setBank(e.target.value)
  }
  const router = useRouter();
  const handleClick = ()=>{
    alert('입력완료')
    const url = `${SERVER}/join`
    const data = {username,password,name,accountNumber,bank}
    const config = {
      headers:{
        "Cache-Control": "no-cache",
        "Content-Type": "application/json",
         Authorization: `Bearer blah ~` ,
        "Access-Control-Allow-Origin": "*",}}
    axios.post(url,data,config)
    .then(res=>{
      alert("결과 :" +JSON.stringify(res.data))
      router.push("/login")
    })
  
      }


 return (<>

   <div>회원가입(계좌생성)</div>
   <div>아이디 입력</div>
    <input type="text" onChange={usernameChange} />

    <div>비밀번호 입력</div>
    <input type="text" onChange={passwordChange}/>

    <div>이름 </div>
    <input type="text" onChange={nameChange}/>

    <div>계좌번호 </div>
    <input type="text" onChange={accountNumberChange}/>
    <div>은행 </div>
    <input type="text" onChange={bankChange}/>

    <button onClick={handleClick}>완료</button>
    <Link href={"/login"}>로그인</Link>

   </>
  );
}
