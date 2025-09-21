export interface UserResponseRegister{
  userId: string;
  email: string;
  nome: string;
  cognome: string;
}

export interface UserRequestRegister{
  email: string;
  nome: string;
  cognome: string;
  password: string;
}

export interface UserResponseLogin{
  token: string;
}

export interface UserRequestLogin{
  email: string;
  password: string;
}
