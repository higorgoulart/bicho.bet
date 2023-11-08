import { useParams } from "react-router-dom";

export default function Account() {
    let { id } = useParams();

    console.log(id);

    return (
        <div className="flex justify-center mt-[5%]">
            <h1 className="card-title text-white">Perfil</h1>
        </div>
    )
}