const API_ENDPOINT = `https://api.canonn.tech`;
const API_LIMIT = 1000;

const capi = axios.create({
	baseURL: API_ENDPOINT,
	headers: {
		'Content-Type': 'application/json',
		'Accept': 'application/json',
	},
});

let sites = {
	apsites: [],
	bmsites: [],
	btsites: [],
	cssites: [],
	fgsites: [],
	fmsites: [],
	gbsites: [],
	gensites: [],
	grsites: [],
	gssites: [],
	gvsites: [],
	gysites: [],
	lssites: [],
	tbsites: [],
	tssites: [],
	twsites: [],
};

const go = async types => {
	const keys = Object.keys(types);
	return (await Promise.all(
			keys.map(type => getSites(type))
	)).reduce((acc, res, i) => {
			acc[keys[i]] = res;
			return acc;
	}, {});
};

const getSites = async type => {
	let records = [];
	let keepGoing = true;
	let API_START = 0;
	while (keepGoing) {
		let response = await reqSites(API_START, type);
		await records.push.apply(records, response.data);
		API_START += API_LIMIT;
		if (response.data.length < API_LIMIT) {
			keepGoing = false;
			return records;
		}
	}
};

const reqSites = async (API_START, type) => {

	let payload = await capi({
		url: `/${type}?_limit=${API_LIMIT}&_start=${API_START}`,
		method: 'get'
	});

	return payload;
};

var canonnEd3d_all = {

	//Define Categories and Static Data
	systemsData: {
		categories: {
			"Sites": {
				"201": {
					name: "(AP) Amphora Plants",
					color: randomColor().replace('#', '').toString()
				},
				"202": {
					name: "(BM) Bark Mounds",
					color: randomColor().replace('#', '').toString()
				},
				"203": {
					name: "(BT) Brain Trees",
					color: randomColor().replace('#', '').toString()
				},
				"204": {
					name: "(CS) Crystalline Shards",
					color: randomColor().replace('#', '').toString()
				},
				"205": {
					name: "(FG) Fungal Gourds",
					color: randomColor().replace('#', '').toString()
				},
				"206": {
					name: "(FM) Fumaroles",
					color: randomColor().replace('#', '').toString()
				},
				"207": {
					name: "(GEN) Generation Ships",
					color: randomColor().replace('#', '').toString()
				},
				"208": {
					name: "(GB) Guardian Beacons",
					color: randomColor().replace('#', '').toString()
				},
				"209": {
					name: "(GR) Guardian Ruins",
					color: randomColor().replace('#', '').toString()
				},
				"210": {
					name: "(GS) Guardian Structures",
					color: randomColor().replace('#', '').toString()
				},
				"211": {
					name: "(GV) Gas Vents",
					color: randomColor().replace('#', '').toString()
				},
				"212": {
					name: "(GY) Geysers",
					color: randomColor().replace('#', '').toString()
				},
				"213": {
					name: "(LS) Lava Spouts",
					color: randomColor().replace('#', '').toString()
				},
				"214": {
					name: "(TB) Thargoid Barnacles",
					color: randomColor().replace('#', '').toString()
				},
				"215": {
					name: "(TS) Thargoid Structure",
					color: randomColor().replace('#', '').toString()
				},
				"216": {
					name: "(TW) Tube Worms",
					color: randomColor().replace('#', '').toString()
				}
			},
			'Unknown Type': {
				'2000': {
					name: 'Unknown Site',
					color: '800000',
				},
			},
		},
		systems: []
	},

	formatSites: async function(data, resolvePromise) {
		sites = await go(data);

		let siteTypes = Object.keys(data);

		for (var i = 0; i < siteTypes.length; i++) {
			for (var d = 0; d < sites[siteTypes[i]].length; d++) {
				let siteData = sites[siteTypes[i]];
				if (siteData[d].system.systemName && siteData[d].system.systemName.replace(' ', '').length > 1) {
					var poiSite = {};
					poiSite['name'] = siteData[d].system.systemName;

					//Check Site Type and match categories
					if (siteTypes[i] == 'apsites') {
						poiSite['cat'] = [201];
					} else if (siteTypes[i] == 'bmsites') {
						poiSite['cat'] = [202];
					} else if (siteTypes[i] == 'btsites') {
						poiSite['cat'] = [203];
					} else if (siteTypes[i] == 'cssites') {
						poiSite['cat'] = [204];
					} else if (siteTypes[i] == 'fgsites') {
						poiSite['cat'] = [205];
					} else if (siteTypes[i] == 'fmsites') {
						poiSite['cat'] = [206];
					} else if (siteTypes[i] == 'gensites') {
						poiSite['cat'] = [207];
					} else if (siteTypes[i] == 'gbsites') {
						poiSite['cat'] = [208];
					} else if (siteTypes[i] == 'grsites') {
						poiSite['cat'] = [209];
					} else if (siteTypes[i] == 'gssites') {
						poiSite['cat'] = [210];
					} else if (siteTypes[i] == 'gvsites') {
						poiSite['cat'] = [211];
					} else if (siteTypes[i] == 'gysites') {
						poiSite['cat'] = [212];
					} else if (siteTypes[i] == 'lssites') {
						poiSite['cat'] = [213];
					} else if (siteTypes[i] == 'tbsites') {
						poiSite['cat'] = [214];
					} else if (siteTypes[i] == 'tssites') {
						poiSite['cat'] = [215];
					} else if (siteTypes[i] == 'twsites') {
						poiSite['cat'] = [216];
					} else {
						poiSite['cat'] = [2000];
					}

					poiSite['coords'] = {
						x: parseFloat(siteData[d].system.edsmCoordX),
						y: parseFloat(siteData[d].system.edsmCoordY),
						z: parseFloat(siteData[d].system.edsmCoordZ),
					};

					// We can then push the site to the object that stores all systems
					canonnEd3d_all.systemsData.systems.push(poiSite);
				}
			}
		}
		document.getElementById("loading").style.display = "none";
		resolvePromise();
	},

	init: function () {

		//Sites
		var p1 = new Promise(function (resolve, reject) {
			canonnEd3d_all.formatSites(sites, resolve);
		});

		Promise.all([p1]).then(function () {
			Ed3d.init({
				container: 'edmap',
				json: canonnEd3d_all.systemsData,
				withFullscreenToggle: false,
				withHudPanel: true,
				hudMultipleSelect: true,
				effectScaleSystem: [20, 500],
				startAnim: false,
				showGalaxyInfos: true,
				cameraPos: [25, 14100, -12900],
				systemColor: '#FF9D00'
			});
		});
	}
};