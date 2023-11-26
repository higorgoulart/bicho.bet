import { useState } from "react";
import { useParams } from "react-router-dom";
import { requestWithToken } from '../../components/api.tsx';
import {  useNavigate } from 'react-router-dom'

export default function Loan(){
    let { id } = useParams();
    const [inputValue, setInputValue] = useState<number>(0);
    const [successMessage, setSuccessMessage] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleButtonClick = async () => {
      const url = `http://localhost:8080/apostadores/sacar/${id}`;
      
      try {
        if (await requestWithToken(url, navigate, 'PUT', inputValue)) {
          setSuccessMessage('Saque realizado com sucesso!');
        }
      } catch (error) {
        setSuccessMessage(JSON.parse(error.message).message);
      }
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setInputValue(Number(e.target.value));
    };
    
    return (
        <div className="flex justify-center mt-[5%]">
            <div className="card w-[100%] bg-secondary space-y-5 mt-3 mx-3 px-5 py-12 mb-24 lg:w-[70%] md:w-[90%] md:px-24 md:py-12 ">
                <h1 className="flex card-title justify-center text-white" >saque</h1>
                <h2 className="flex justify-center">Mas já vai sacar? Você ainda pode ganhar tanto!</h2>
                <span>R$</span>
                <input className="bg-primary py-2 p-2 rounded-md" type="number" value={inputValue} onChange={handleInputChange} min="0"/>
                <button className="btn btn-accent text-white glow-blue" onClick={handleButtonClick}>sacar</button>
                {successMessage && <p>{successMessage}</p>}
            </div>
        </div>
    )
}