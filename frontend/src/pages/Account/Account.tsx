import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";


export default function Account() {
    let { id } = useParams();

    //TODO pegar a função que o ferreira fez e pegar o token de cada usuário
    const headers = {'Authorization': '',
                     'Access-Control-Allow-Origin': '*',
                     'Access-Control-Allow-Methods': "*"};

    console.log(id);

    const url = `http://localhost:8080/apostadores/${id}`;
    const [data, setData] = useState<any[]>([]);

    useEffect(() => {
        fetch(url, { headers })
          .then((res) => res.json())
          .then((d) => {
            const parsedData = [];
            parsedData.push(d);
            setData(parsedData);
          });
      }, [id]);

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
                        }

                        )}
                    <div className="card-actions">
                        <button className="btn btn-accent text-white glow-blue">Depositar</button>
                        <button className="btn btn-info text-white glow-pink mx-5">Saque</button>
                        <button className="btn btn-accent text-white glow-blue">Emprestimo</button>
                        
                        <div>
                        </div>
                    </div>
                </div>
            </div>
    )
}