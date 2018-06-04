import {Injectable} from "@angular/core";
import {Hero} from "./hero";
import {Http} from "@angular/http";
import {Headers} from "@angular/http";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class HeroService {

  private heroUrl = "api/heroes";
  private headers = new Headers({"content-type": "application/json"});

  constructor(private http: Http){

  }

  getHeroes(): Promise<Hero[]>{
    // return Promise.resolve(HEROES);
    console.log("get heroes");
    return this.http.get(this.heroUrl).toPromise()
      .then(response => response.json() as Hero[])
      .catch(this.handError);
  };
  private handError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  getHeroesSlowly(): Promise<Hero[]> {
    return new Promise<Hero[]>(resolve => {
      setTimeout(() => resolve(this.getHeroes()), 2000)
    })
  };

  getHero(id: number): Promise<Hero> {
    const url = `${this.heroUrl}/${id}`;
    return this.http.get(url).toPromise()
      .then(response => response.json() as Hero)
      .catch(this.handError);
    // return this.getHeroes().then(heroes => heroes.find(hero => hero.id === id))
  }

  update(hero: Hero): Promise<Hero> {
    const  url = `${this.heroUrl}/${hero.id}`;
    return this.http.put(url, JSON.stringify(hero), {headers: this.headers})
      .toPromise()
      .then(() => hero)
      .catch(this.handError);
  }

  create(name: string): Promise<Hero> {
    return this.http.post(this.heroUrl, JSON.stringify({name: name}), {headers: this.headers})
      .toPromise()
      .then(res => res.json() as Hero)
      .catch(this.handError);
  }

  delete(id: number): Promise<void> {
    const url = `${this.heroUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handError);
  }
}
