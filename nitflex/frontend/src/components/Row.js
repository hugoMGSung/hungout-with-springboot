import React from 'react';
import Fade from 'react-awesome-reveal';
import Slider from 'react-slick';
import * as Icon from 'react-bootstrap-icons';

// css가 아직 오류~!!
// import 'slick-carousel/slick/slick.css';
// import 'slick-carousel/slick/slick-theme.css';

const Row = ({ 
	actions,
	adventures,
	comedys,
	documentarys,
	familys,
	fantasys,
	horrors,
	populars,
}) => {
	console.log("props의 액션값:", actions);
	console.log("props의 adventures:", adventures);

	const RightArrow = (props) => {
		const { onClick } = props;
		return (
			<div
				className='slick_button'
				style={{ marginLeft: '99%' }}
				onClick={onClick}
			><Icon.ChevronCompactRight style={{height: '100%'}} /></div>
		);
	}

	const LeftvArrow = (props) => {
		const { onClick } = props;
		return (
		<div
			className='slick_button'
			onClick={onClick}
		><Icon.ChevronCompactLeft style={{height: '100%'}} /></div>
		);
	}

	const settings = {  // Slider settings
		infinite : true,
		slidesToShow : 1,
		slidesToScroll : 5,
		variableWidth : true,
		nextArrow: <RightArrow />,
		prevArrow: <LeftvArrow />
	};

	return (
		<div>
		<Fade bottom>
			<div className='rowStyle'>
			<h3 className='titleFont'>인기 콘텐츠</h3>
			<div className="row">
				<div className='col'>
				<Slider {...settings}>
					{populars
					? populars.map((item) => {
						return (
							<div className='slick'>
							{/* <DetailContent id={item.id} movie={item}></DetailContent> */}
							</div>
						);
						})
					: ''}
				</Slider>
				</div>
			</div>
			</div>
		</Fade>
		<Fade bottom>
			<div className='rowStyle'>
			<h3 className='titleFont'>액션</h3>
			<div className="row">
				<div className='col'>
				<Slider {...settings}>
					{actions
					? actions.map((item) => {
						return (
							<div className='slick'>
							{/* <DetailContent id={item.id} movie={item}></DetailContent> */}
							</div>
						);
						})
					: ''}
				</Slider>
				</div>
			</div>
			</div>
		</Fade>
		<Fade bottom>
			<div className='rowStyle'>
			<h3 className='titleFont'>어드벤쳐</h3>
			<div className="row">
				<div className='col'>
				<Slider {...settings}>
					{adventures
					? adventures.map((item) => {
						return (
							<div className='slick'>
							{/* <DetailContent id={item.id} movie={item}></DetailContent> */}
							</div>
						);
						})
					: ''}
				</Slider>
				</div>
			</div>
			</div>
		</Fade>
		<Fade bottom>
			<div className='rowStyle'>
			<h3 className='titleFont'>코미디</h3>
			<div className="row">
				<div className='col'>
				<Slider {...settings}>
					{comedys
					? comedys.map((item) => {
						return (
							<div className='slick'>
							{/* <DetailContent id={item.id} movie={item}></DetailContent> */}
							</div>
						);
						})
					: ''}
					</Slider>
				</div>
			</div>
			</div>
		</Fade>
		<Fade bottom>
			<div className='rowStyle'>
			<h3 className='titleFont'>다큐멘터리</h3>
			<div className="row">
				<div className='col'>
				<Slider {...settings}>
					{documentarys
					? documentarys.map((item) => {
						return (
							<div className='slick'>
							{/* <DetailContent id={item.id} movie={item}></DetailContent> */}
							</div>
						);
						})
					: ''}
					</Slider>
				</div>
			</div>
			</div>
		</Fade>
		<Fade bottom>
			<div className='rowStyle'>
			<h3 className='titleFont'>가족영화</h3>
			<div className="row">
				<div className='col'>
				<Slider {...settings}>
					{familys
					? familys.map((item) => {
						return (
							<div className='slick'>
							{/* <DetailContent id={item.id} movie={item}></DetailContent> */}
							</div>
						);
						})
					: ''}
					</Slider>
				</div>
			</div>
			</div>
		</Fade>
		<Fade bottom>
			<div className='rowStyle'>
			<h3 className='titleFont'>판타지</h3>
			<div className="row">
				<div className='col'>
				<Slider {...settings}>
					{fantasys
					? fantasys.map((item) => {
						return (
							<div className='slick'>
							{/* <DetailContent id={item.id} movie={item}></DetailContent> */}
							</div>
						);
						})
					: ''}
					</Slider>
				</div>
			</div>
			</div>
		</Fade>
		<Fade bottom>
			<div className='rowStyle'>
			<h3 className='titleFont'>호러</h3>
			<div className="row">
				<div className='col'>
				<Slider {...settings}>
					{horrors
					? horrors.map((item) => {
						return (
							<div className='slick'>
							{/* <DetailContent id={item.id} movie={item}></DetailContent> */}
							</div>
						);
						})
					: ''}
					</Slider>
				</div>
				</div>
			</div>
			</Fade>
		</div>
	);
};

export default Row;