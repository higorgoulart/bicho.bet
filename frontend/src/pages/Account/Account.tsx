import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import { requestWithToken } from '../../components/api.tsx';
import { Link, useNavigate } from 'react-router-dom'

export default function Account() {
    let { id } = useParams();
    const navigate = useNavigate();
    
    console.log(id);

    const url = `http://localhost:8080/apostadores/${id}`;
    const [data, setData] = useState<any[]>([]);

    useEffect(() => {
        fetch();
    }, [id]);

    const fetch = async () => {
        const data = await requestWithToken(url, navigate);
        const parsedData = [];
        parsedData.push(data);
        setData(parsedData);
    }

    return (
            <div className="flex justify-center mt-[5%]">
                <div className="card w-[100%] bg-secondary space-y-5 mt-3 mx-3 px-5 py-12 mb-24 lg:w-[70%] md:w-[90%] md:px-24 md:py-12 ">
                    <h1 className="card-title justify-center text-white" >Perfil</h1>

                        {data.map((dataObj, index) => {
                            return(
                                <div className="account">
                                    <h2 className="flex justify-center text-3xl text-white">Olá, {dataObj.nome}!</h2><br />
                                    <div className="account-balance">
                                        <p className="text-xl">Seu Saldo: R$ {dataObj.saldo}</p><br />
                                    </div>
                                    <div className="account-info">
                                    <h1 className="text-xl">Seus Dados:</h1>
                                        <div className="info">
                                            <div className="bg-primary py-2 rounded-md p-2">
                                                <p>Nome: {dataObj.nome}</p>
                                            </div>
                                            <div className="py-2 p-2">
                                                <p>Email: {dataObj.email}</p>
                                            </div>
                                            <div className="py-2 bg-primary rounded-md p-2">
                                                <p>CPF: {dataObj.cpf}</p>
                                            </div>
                                            <div className="py-2 p-2">
                                                <p>Depositado: R$ {dataObj.depositado}</p>
                                            </div>
                                            <div className="bg-primary py-2 rounded-md p-2">
                                                <p>Divida: R$ {dataObj.divida}</p>
                                            </div>
                                            <div className="py-2 p-2">
                                                <p>Limite: R$ {dataObj.limite}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            )
                        })}
                    <div className="card-actions">
                        <Link to={`/perfil/deposito/${id}`} className="btn btn-accent text-white glow-blue">Depositar</Link>
                        <Link to={`/perfil/saque/${id}`} className="btn btn-info text-white glow-pink mx-5">Saque</Link>
                        <Link to={`/perfil/emprestimo/${id}`} className="btn btn-accent text-white glow-blue">Emprestimo</Link>
                    </div>
                </div>
            </div>
    )
}