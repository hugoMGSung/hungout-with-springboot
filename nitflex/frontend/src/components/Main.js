import React, { Component } from 'react';

import Row from './Row';

import '../styles/Detail.css'

class MainPage extends Component {
	constructor(props) {
		super(props);
		console.log("Main props", props);
		// OAuth2 로그인했을때 아이디값이 queryString으로 날아옴! 해당부분 받아서 세션 저장하는 부분
		//console.log(props.location.search); // 아직 없음

		this.state = {
			// genre: ['28', '12', '35', '99', '10751', '14', '27'],
			genre: [
				"Action",
				"Adventure",
				"Comedy",
				"Documentary",
				"Family",
				"Fantasy",
				"Horror",
			],
			populars: '',
		};
	}

	componentDidMount() {
		this.getAllMoviesByGenre();
		this.getMainPopularMovies();
	}

	// componentDidMount() 에서 처리할 두 메서드
	getAllMoviesByGenre = async () => { 

	};

	getMainPopularMovies = async () => { 
		
	};
	
	render() {
		return (
			<div style={{ backgroundColor: "#181818", paddingRight: "15px" }}>
				<div className="row">
					<div className="col-12">
						<iframe
							src="https://youtube.com/embed/UIA1QoGATHY"
							title="Youtube Video Player"
							className="video"
							allowFullScreen
							style={{ width: "100%", height: "80vh" }}
						/>
					</div>
				</div>
				<div className="row">
					<div className="col">
						<button
							type="button"
							className="btn btn-light"
							style={{ margin: 10 }}
							children="▶ 재생"
						/>
						<button
							type="button"
							className="btn btn-secondary"
							children="상세 정보"
						/>
					</div>
				</div>
				<div className="row">
					<div className="col">
						<Row />
						{/*
							actions={this.state.actions}
							adventures={this.state.adventures}
							comedys={this.state.comedys}
							documentarys={this.state.documentarys}
							familys={this.state.familys}
							fantasys={this.state.fantasys}
							horrors={this.state.horrors}
							populars={this.state.populars}
						/> */}
					</div>
				</div>
			</div>
		);
	}
}

export default MainPage;