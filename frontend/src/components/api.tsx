const getAuthToken = () => {
  return localStorage.getItem('jwtToken');
};
  
export const requestWithToken = async (url, navigate, method = 'GET', data = {}) => {
  const token = getAuthToken();

  const headers = {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${token}`,
  };

  const requestOptions = {
    method,
    headers,
  };

  if (method !== 'GET') {
    requestOptions.body = JSON.stringify(data);
  }

  try {
    const response = await fetch(url, requestOptions);

    if (!response.ok) {
      if (response.status === 401) {
        navigate(`/login`);
      }

      const errorMessage = await response.text();
      throw new Error(errorMessage);
    }

    return response.json();
  } catch (error) {
    throw new Error(error.message);
  }
};

export default requestWithToken;
  