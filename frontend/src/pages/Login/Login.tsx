import React, { useState } from 'react';

const Login = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        nome: '',
        role: [''],
        email: '',
        cpf: '',
    });

    const [error, setError] = useState(null);
    const [formMode, setFormMode] = useState('signin'); // 'signin' or 'signup'

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const url = formMode === 'signin' ? 'http://localhost:8080/auth/signin' : 'http://localhost:8080/auth/signup';
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    ...formData,
                    role: [formData.role],
                }),
            });

            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(errorMessage);
            }

            const data = await response.json();
            // Assuming the server returns a JWT in the 'token' property
            const { token } = data;

            // Store the JWT in localStorage or a secure storage mechanism
            localStorage.setItem('jwtToken', token);

            // Redirect or perform other actions upon successful login or signup
            console.log(`${formMode === 'signin' ? 'Login' : 'Signup'} successful`);
        } catch (error) {
            setError(error.message);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const toggleFormMode = () => {
        setFormMode(formMode === 'signin' ? 'signup' : 'signin');
        setError(null);
    };

    return (
        <div className="min-h-screen flex items-center justify-center">
            <div className="max-w-md w-full p-6 bg-secondary rounded-md shadow-md">
                <h2 className="text-2xl font-bold mb-4">{formMode === 'signin' ? 'Login' : 'Cadastre-se'}</h2>
                {error && <p className="text-red-500 mb-4">{error}</p>}
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label htmlFor="username" className="block text-white text-sm font-bold mb-2">
                            Username:
                        </label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            value={formData.username}
                            onChange={handleInputChange}
                            className="w-full p-2 border rounded-md"
                            placeholder=" seu usuário aqui"
                        />
                    </div>
                    <div className="mb-4">
                        <label htmlFor="password" className="block text-white text-sm font-bold mb-2">
                            Senha:
                        </label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            value={formData.password}
                            onChange={handleInputChange}
                            className="w-full p-2 border rounded-md"
                            placeholder=" sua senha aqui"
                        />
                    </div>
                    {formMode === 'signup' && (
                        <>
                            
                            <div className="mb-4">
                                <label htmlFor="nome" className="block text-white text-sm font-bold mb-2">
                                    Nome:
                                </label>
                                <input
                                    type="text"
                                    id="nome"
                                    name="nome"
                                    value={formData.nome}
                                    onChange={handleInputChange}
                                    className="w-full p-2 border rounded-md"
                                    placeholder=" seu nome completo aqui aqui"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="role" className="block text-white text-sm font-bold mb-2">
                                    Role:
                                </label>
                                <input
                                    type="text"
                                    id="role"
                                    name="role"
                                    value={formData.role}
                                    onChange={handleInputChange}
                                    className="w-full p-2 border rounded-md"
                                    placeholder=" seu cargo aqui"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="email" className="block text-white text-sm font-bold mb-2">
                                    Email:
                                </label>
                                <input
                                    type="email"
                                    id="email"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleInputChange}
                                    className="w-full p-2 border rounded-md"
                                    placeholder=" seu email aqui"
                                />
                            </div>
                            <div className="mb-4">
                                <label htmlFor="cpf" className="block text-white text-sm font-bold mb-2">
                                    CPF:
                                </label>
                                <input
                                    type="text"
                                    id="cpf"
                                    name="cpf"
                                    value={formData.cpf}
                                    onChange={handleInputChange}
                                    className="w-full p-2 border rounded-md"
                                    placeholder=" seu CPF aqui"
                                />
                            </div>
                        </>
                    )}
                    <button
                        type="submit"
                        className="btn w-full text-white rounded-full bg-accent hover:bg-info "
                    >
                        {formMode === 'signin' ? 'Login' : 'Cadastre-se'}
                    </button>
                </form>
                <hr className="my-6" />
                <p className="text-center">
                    {formMode === 'signin' ? "Não tem uma conta?" : 'Já tem uma conta?'}
                    <button
                        type="button"
                        className="text-blue-500 ml-1 underline"
                        onClick={toggleFormMode}
                    >
                        {formMode === 'signin' ? 'Cadastre-se' : 'Login'}
                    </button>
                </p>
            </div>
        </div>
    );
};

export default Login;
