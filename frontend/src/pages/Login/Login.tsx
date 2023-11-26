import React, { useState } from 'react';

const Login = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
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
                body: JSON.stringify(formData),
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
                            Password:
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
                    <button
                        type="submit"
                        className={`w-full bg-${formMode === 'signin' ? 'blue' : 'green'}-500 text-white p-2 rounded-md hover:bg-${formMode === 'signin' ? 'blue' : 'green'}-600 focus:outline-none`}
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
