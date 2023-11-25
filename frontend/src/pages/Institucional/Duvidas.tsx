import { useState } from 'react';

export function Duvidas() {
    const [selectedQuestion, setSelectedQuestion] = useState<number | null>(null);

    const questions = [
        {
            question: 'Como faço para apostar?',
            answer: 'Para apostar, você precisa se registrar em uma banca de Jogo do Bicho. Após o registro, você pode fazer suas apostas online.'
        },
        {
            question: 'Como faço para me registrar?',
            answer: 'Para se registrar, você precisa ter mais de 18 anos e fornecer seus dados pessoais, como nome, endereço, telefone e e-mail. Você também precisa criar um nome de usuário e senha.'
        },
        {
            question: 'Como faço para depositar?',
            answer: 'Para depositar, você precisa ter uma conta bancária e um cartão de crédito ou débito. Você também pode usar carteiras digitais, como PayPal, PicPay ou GooglePay.'
        },
        {
            question: 'Como faço para sacar?',
            answer: 'Para sacar, você precisa ter uma conta bancária. Você pode sacar diretamente para sua conta bancária ou usar carteiras digitais, como PayPal, PicPay ou GooglePay.'
        },
        {
            question: 'Quais são os horários de sorteio?',
            answer: 'Os horários de sorteio variam de acordo com a banca ou região. É importante verificar os horários de sorteio da banca onde você está jogando.'
        },
        {
            question: 'Quais são os prêmios?',
            answer: 'Os prêmios variam de acordo com a banca ou região. É importante verificar as regras de premiação da banca onde você está jogando.'
        },
        {
            question: 'Como faço para entrar em contato com a banca?',
            answer: 'Você pode entrar em contato com a banca por telefone, e-mail ou chat ao vivo. Você também pode visitar a banca pessoalmente.'
        },
        {
            question: 'Como faço para entrar em contato com o Bicho.bet?',
            answer: 'Você pode entrar em contato com o Bicho.bet por telefone, e-mail ou chat ao vivo.'
        },
    ];

    const handleQuestionClick = (index: number) => {
        setSelectedQuestion(index === selectedQuestion ? null : index);
    };

    return (
        <div className="flex justify-center mt-[5%]">
            <div className='card w-[100%] bg-secondary space-y-5 mt-3 mx-3 px-5 py-12 mb-24 lg:w-[70%] md:w-[90%] md:px-24 md:py-12 '>
                <h1 className="text-center text-2xl mb-4 font-bold">Dúvidas frequentes</h1>
                {questions.map((q, index) => (
                    <div key={index} className="cursor-pointer" onClick={() => handleQuestionClick(index)}>
                        <h2 className="text-lg font-bold">- {q.question}</h2>
                        {selectedQuestion === index && <p className='mt-5 mb-8 pl-4'>{q.answer}</p>}
                    </div>
                ))}
            </div>
        </div>
    )
}
