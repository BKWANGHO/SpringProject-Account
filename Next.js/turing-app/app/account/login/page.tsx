'use client'
import axios from "axios"
import Link from "next/link"
import { useRouter } from "next/navigation"
import { useState } from "react"

const SERVER = 'http://localhost:8080'
export default function Login(){
  
  const [username,setUsername] = useState('')
  const [password,setPassword] = useState('')
  
  
  const usernameChange = (e:any) => {
        setUsername(e.target.value)
      }
      
      const passwordChange = (e:any)=>{
        setPassword(e.target.value)
      }
      
      const router = useRouter();
      const handleClick = ()=>{
        alert('입력완료')
        const url = `${SERVER}/login`
        const data = {username,password}
        const config = {
          headers:{
            "Cache-Control": "no-cache",
            "Content-Type": "application/json",
             Authorization: `Bearer blah ~` ,
            "Access-Control-Allow-Origin": "*",}}
        axios.post(url,data,config)
        .then(res=>{
          const result = res.data.Messege
          alert(result)
          if(result === 'SUCCESS'){
            router.push("/service")
          }
        })
      
          }
    
    return(<>
    <h1>로그인</h1>
    <h3>아이디 입력</h3>
    <input type="text" onChange={usernameChange} />

    <div>비밀번호 입력</div>
    <input type="text" onChange={passwordChange}/>

    <button onClick={handleClick}>완료</button><br />

    </>)
}