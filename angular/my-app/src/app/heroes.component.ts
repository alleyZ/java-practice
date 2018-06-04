import { Component, OnInit } from '@angular/core';
import {Hero} from "./hero";
import {HeroService} from "./hero.service";
import {Router} from "@angular/router";

@Component({
  selector: 'my-heroes',
  templateUrl: "./heroes.component.html",
  styleUrls:['./heroes.component.css']
})
export class HeroesComponent implements OnInit{
  title = 'Tour of Heroes';
  // hero: Hero = {id: 1, name: "hero"};
  heroes: Hero[];
  selectedHero: Hero;

  constructor(
    private heroService: HeroService,
    private router: Router
    ) {
    // this.heroes = this.heroService.getHeroes();
  };

  ngOnInit(): void {
    this.getHeroes();
  }

  getHeroes(): void {
    this.heroService.getHeroes().then(heroes => this.heroes = heroes);
  }

  onSelect(hero: Hero) :void {
    this.selectedHero = hero;
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectedHero.id]);
  }

  add(name: string): void{
    name = name.trim();
    if(!name) return;
    this.heroService.create(name)
      .then(hero => {
        this.heroes.push(hero);
        this.selectedHero = null;
      })
  }

  delete(hero: Hero): void {
    this.heroService.delete(hero.id)
      .then(() => {
        this.heroes = this.heroes.filter(h => h != hero);
        if(this.selectedHero === hero) {this.selectedHero = null};
      });
  }
}
