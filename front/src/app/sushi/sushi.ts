export interface Sushi {
  id?: number;
  nom: string;
  prix: number;
  categorie: string;
  image?: string;
  qty?: number;
  stock?: number;
  disponible?: boolean;
}