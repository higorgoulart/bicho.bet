import { useEffect, useState } from "react";
import  Animal  from "../NewBet/Animal";

import abacate from "../../assets/vegan/abacate.svg";
import abobora from "../../assets/vegan/abobora.svg";
import alho_poro from "../../assets/vegan/alho_poro.svg";
import alho from "../../assets/vegan/alho.svg";
import banana from "../../assets/vegan/banana.svg";
import batata from "../../assets/vegan/batata.svg";
import beringela from "../../assets/vegan/beringela.svg";
import brocolis from "../../assets/vegan/brocolis.svg";
import cenoura from "../../assets/vegan/cenoura.svg";
import cereja from "../../assets/vegan/cereja.svg";
import cogumelos from "../../assets/vegan/cogumelos.svg";
import kiwi from "../../assets/vegan/kiwi.svg";
import laranja from "../../assets/vegan/laranja.svg";
import limao from "../../assets/vegan/limao.svg";
import maca from "../../assets/vegan/maca.svg";
import manga from "../../assets/vegan/manga.svg";
import melancia from "../../assets/vegan/melancia.svg";
import milho from "../../assets/vegan/milho.svg";
import mirtilo from "../../assets/vegan/mirtilo.svg";
import morango from "../../assets/vegan/morango.svg";
import pera from "../../assets/vegan/pera.svg";
import pimenta from "../../assets/vegan/pimenta.svg";
import pimentao from "../../assets/vegan/pimentao.svg";
import repolho from "../../assets/vegan/repolho.svg";
import uva from "../../assets/vegan/uva.svg";


const FrutasEVegetais = [
    { id: 1, title: "Abacate", numbers: "01, 02, 03, 04", src: abacate },
    { id: 2, title: "Abóbora", numbers: "05, 06, 07, 08", src: abobora },
    { id: 3, title: "Alho Poró", numbers: "09, 10, 11, 12", src: alho_poro },
    { id: 4, title: "Alho", numbers: "13, 14, 15, 16", src: alho },
    { id: 5, title: "Banana", numbers: "16, 18, 19, 20", src: banana },
    { id: 6, title: "Batata", numbers: "21, 22, 23, 24", src: batata },
    { id: 7, title: "Beringela", numbers: "25, 26, 27, 28", src: beringela },
    { id: 8, title: "Brócolis", numbers: "29, 30, 31, 32", src: brocolis },
    { id: 9, title: "Cenoura", numbers: "33, 34, 35, 36", src: cenoura },
    { id: 10, title: "Cereja", numbers: "37, 38, 39, 40", src: cereja },
    { id: 11, title: "Cogumelos", numbers: "41, 42, 43, 44", src: cogumelos },
    { id: 12, title: "Kiwi", numbers: "45, 46, 47, 48", src: kiwi },
    { id: 13, title: "Laranja", numbers: "49, 50, 51, 52", src: laranja },
    { id: 14, title: "Limão", numbers: "53, 54, 55, 56", src: limao },
    { id: 15, title: "Maçã", numbers: "57, 58, 59, 60", src: maca },
    { id: 16, title: "Manga", numbers: "61, 62, 63, 64", src: manga },
    { id: 17, title: "Melância", numbers: "65, 66, 67, 68", src: melancia },
    { id: 18, title: "Milho", numbers: "69, 70, 71, 72", src: milho },
    { id: 19, title: "Mirtilo", numbers: "73, 74, 75, 76", src: mirtilo },
    { id: 20, title: "Morango", numbers: "77, 78, 79, 80", src: morango },
    { id: 21, title: "Pêra", numbers: "81, 82, 83, 84", src: pera },
    { id: 22, title: "Pimenta", numbers: "85, 86, 87, 88", src: pimenta },
    { id: 23, title: "Pimentão", numbers: "89, 90, 91, 92", src: pimentao },
    { id: 24, title: "Repolho", numbers: "93, 94, 95, 96", src: repolho },
    { id: 25, title: "Uva", numbers: "97, 98, 99, 00", src: uva }
];

export function Veganos() {
    const [betType, setBetType] = useState("");
    const [selectedFruits, setSelectedFruits] = useState([]);

    useEffect(() => {
        setBetType(handleTitle());
    }, [selectedFruits]);

    const handleSelectedFruits = (id: number, src: string) => {
        const newFruits = [...selectedFruits];
        const indexFruits = newFruits.indexOf(src);

        if (indexFruits > -1) {
            newFruits.splice(indexFruits, 1);
        } else if (newFruits.length < 5) {
            newFruits.push(src);
        }

        setSelectedFruits(newFruits);

        const newNumbers = [...numbers];
        const indexNumbers = newNumbers.indexOf(id);

        if (indexNumbers > -1) {
            newNumbers.splice(indexNumbers, 1);
        } else if (newNumbers.length < 5) {
            newNumbers.push(id);
        }

        setNumbers(newNumbers);
    }

    const renderSelectedFruits = () => {
        return selectedFruits.map(fruta => <Animal key={fruta} title="" numbers='' src={fruta} />)
    }

    const handleTitle = () => {
        switch (selectedFruits.length) {
            case 5: return "Quina";
            case 4: return "Quadra";
            case 3: return "Terno";
            case 2: return "Duque";
            case 1: return "Grupo";
            default: return "";
        }
    }

    const renderFruis = () => {
        return FrutasEVegetais.map(fruta =>
            <Animal
                    key={fruta.id}
                    title={fruta.title}
                    numbers={fruta.numbers}
                    src={fruta.src}
                    onClick={() => handleSelectedFruits(fruta.id, fruta.src)}
                    color="black"
                />
        );
    }


    return (
    <div>
        <div className="flex bg-white flex-wrap py-12 justify-center text-black">
            {renderFruis()}
        </div>
        <div className="flex flex-col bg-white items-center pb-24 min-w-full">
            <h1 className="text-black font-black">Sua aposta:</h1>
            <ul className="flex">
                {renderSelectedFruits()}
            </ul>
            <h1 className="card-title text-black self-center mt-2">{betType}</h1>
        </div>
    </div>
    )
}
