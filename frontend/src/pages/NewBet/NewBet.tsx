import { useLocation, useParams } from "react-router-dom";
import { useMemo } from "react";
import Animal from "./Animal.tsx";
import ostrich from "../../assets/ostrich.svg";
import eagle from "../../assets/eagle.svg";
import donkey from "../../assets/donkey.svg";
import butterfly from "../../assets/butterfly.svg";
import dog from "../../assets/dog.svg";
import goat from "../../assets/goat.svg";
import ram from "../../assets/ram.svg";
import camel from "../../assets/camel.svg";
import snake from "../../assets/snake.svg";
import rabbit from "../../assets/rabbit.svg";
import horse from "../../assets/horse.svg";
import elephant from "../../assets/elephant.svg";
import rooster from "../../assets/rooster.svg";
import cat from "../../assets/cat.svg";
import aligator from "../../assets/aligator.svg";
import lion from "../../assets/lion.svg";
import monkey from "../../assets/monkey.svg";
import pig from "../../assets/pig.svg";
import peacock from "../../assets/peacock.svg";
import turkey from "../../assets/turkey.svg";
import bull from "../../assets/bull.svg";
import tiger from "../../assets/tiger.svg";
import bear from "../../assets/bear.svg";
import deer from "../../assets/deer.svg";
import cow from "../../assets/cow.svg";

function useQuery() {
    const { search } = useLocation();

    return useMemo(() => new URLSearchParams(search), [search]);
}

export default function NewBet() {
    let { id } = useParams();
    let query = useQuery();
    const type = query.get("tipo");

    return (
        <div className="flex justify-center">
            {type === "1"
                ? (
                    <div className="flex flex-wrap rounded border-4 border-secondary p-8 basis-[39.4rem]">
                        <Animal title="Avestruz" numbers="01, 02, 03, 04" src={ostrich} />
                        <Animal title="Águia" numbers="05, 06, 07, 08" src={eagle} />
                        <Animal title="Burro" numbers="09, 10, 11, 12" src={donkey} />
                        <Animal title="Borboleta" numbers="13, 14, 15, 16" src={butterfly} />
                        <Animal title="Cachorro" numbers="16, 18, 19, 20" src={dog} />
                        <Animal title="Cabra" numbers="21, 22, 23, 24" src={goat} />
                        <Animal title="Carneiro" numbers="25, 26, 27, 28" src={ram} />
                        <Animal title="Camelo" numbers="29, 30, 31, 32" src={camel} />
                        <Animal title="Cobra" numbers="33, 34, 35, 36" src={snake} />
                        <Animal title="Coelho" numbers="37, 38, 39, 40" src={rabbit} />
                        <Animal title="Cavalo" numbers="41, 42, 43, 44" src={horse} />
                        <Animal title="Elefante" numbers="45, 46, 47, 48" src={elephant} />
                        <Animal title="Galo" numbers="49, 50, 51, 52" src={rooster} />
                        <Animal title="Gato" numbers="53, 54, 55, 56" src={cat} />
                        <Animal title="Jacaré" numbers="57, 58, 59, 60" src={aligator} />
                        <Animal title="Leão" numbers="61, 62, 63, 64" src={lion} />
                        <Animal title="Macaco" numbers="65, 66, 67, 68" src={monkey} />
                        <Animal title="Porco" numbers="69, 70, 71, 72" src={pig} />
                        <Animal title="Pavão" numbers="73, 74, 75, 76" src={peacock} />
                        <Animal title="Peru" numbers="77, 78, 79, 80" src={turkey} />
                        <Animal title="Touro" numbers="81, 82, 83, 84" src={bull} />
                        <Animal title="Tigre" numbers="85, 86, 87, 88" src={tiger} />
                        <Animal title="Urso" numbers="89, 90, 91, 92" src={bear} />
                        <Animal title="Veado" numbers="93, 94, 95, 96" src={deer} />
                        <Animal title="Vaca" numbers="97, 98, 99, 00" src={cow} />
                    </div>
                )
                : (<div></div>)}
        </div>
    );
}