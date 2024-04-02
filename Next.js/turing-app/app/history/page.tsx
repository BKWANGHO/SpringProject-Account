'use client'

import axios from "axios"
import { useState } from "react"


interface IHistory {
accountNumber : number,
balance : number,
totalbalance : number,
transation : string

}

export  function HistoryColumns(props:IHistory) {
    return (
        <tr key={props.accountNumber}>
            <td>{props.balance}</td>
            <td>{props.totalbalance}</td>
            <td>{props.transation}</td>
        </tr>
    );
}


export  function HistoryRows(){
    return [
        {id:1,company : 'Alfreds Futterkiste',contact :'Maria Anders',country :'Germany' },
        {id:2,company : 'Centro comercial Moctezuma',contact :'Francisco Chang',country :'Mexico' },
        {id:3,company : 'Ernst Handel',contact :'Roland Mendel',country :'Austria' },
        {id:4,company : 'Island Trading',contact :'Helen Bennett',country :'UK' }
    ]
}


const SERVER = 'http://localhost:8080'
export default function Deposit(){

    const [accountNumber,setAccountNumber] = useState(''    )

    
    const handleAccountNumber = (e : any)=>{
        setAccountNumber(e.target.value)
    }
    const handleClick = ()=>{
        alert('입력완료')
        const url = `${SERVER}/history`
        const data = {accountNumber}
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
    <div>거래내역</div>
    <div>계좌번호</div>
    <input type="text" onChange={handleAccountNumber} /><br />
    <button onClick={handleClick}>완료</button>

    <table>
                <thead>
                <tr>
                    <th>Company</th>
                    <th>Contact</th>
                    <th>Country</th>
                </tr>
                </thead>
                <tbody>
                {HistoryRows().map(v => HistoryColumns(v))}
                </tbody>
            </table>



    </>)
}