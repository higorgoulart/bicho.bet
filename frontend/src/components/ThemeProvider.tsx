import  { useState } from 'react';
import  ThemeContext  from './ThemeContext';


export function ThemeProvider({  children }) {
  const [theme] = useState('bg-white');


  return (
    <ThemeContext.Provider value={ theme }>
      {children}
    </ThemeContext.Provider>
  );
}
