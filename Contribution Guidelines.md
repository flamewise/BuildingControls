Source: [Atlassian Gitflow workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)

***

# Branching

Contributors pull from the `develop` branch to implement a `feature`. When completed, a pull request will be submitted and another contributor will review the submission before accepting a merge back into the `develop` branch.

When ready for production, `release` branches are merged into the `develop` and `main` branches.

Any `hotfix` will be branched from `main` and merged into the `develop` and `main` branches.

Branches will follow the naming structure:

| Branch | Purpose | Name |
| --- | --- | --- |
| Main | Stores release history | `main` |
| Develop | Integration branch of features | `develop` |
| Feature | Branched from `develop` for implementing features (i.e., user stories, etc.). <br/> Merged back into `develop` when implemented and **deleted** afterwards. | `feat/#[issue-no]-feature-name` |
| Release | Branched from `develop` for production builds. <br/> Merged into `main` for production and into `develop` to accommodate any changes that have occurred during release. Deleted after merge. | `release/v[build-no + 1.0]` |
| Hotfix | Maintenance branch. <br/> Branched from `main` for fixes. <br/> Merged back into `main` and `develop` to implement changes. Deleted after merge. | `hotfix/v[build-no + 0.1]` |

# Commit Messages

## Format
> #[issue-no] [type]: commit message

## Types

Commit messages can be of the following types:

| Commit Type | Title | Description |
| --- | --- | --- |
| `feat` | Feature | For adding/contributing to a new feature |
| `fix` | Bug Fixes | For bug fixes |
| `refactor` | Refactor | For code restructuring - does not change any behaviour |
| `perf` | Performance Improvements | For code changes that improve performance - alternative to `refactor` |
| `style` | Styles | For any changes that do not affect meaning (white-space, formatting, missing semi-colons, etc.) |
| `test` | Tests | For adding missing tests or correcting existing tests |
| `docs` | Documentation | For updates to documentation (e.g. wiki-resources) |
| `build` | Builds | For any changes to build tools, dependencies, project version, etc. |
| `ci` | Continuous Integrations | For changes to the CI pipeline, including any configuration files and scripts |
| `ops` | Operations | For any changes to infrastructure, deployment, backup, recovery, etc. |
| `chore` | Chores | Miscellaneous commits - for changes that do not modify src or test files |
| `long-term` | Long Term | Long Term Tickets - for tickets related to research or is not bounded by sprints |

# Merging

**During development, commits directly to `develop`/`main` should be avoided and must undergo some prior review.**

- Submit a pull request using the commit types above and _ideally_ try to find a reviewer
- Ensure merge passes all defined tests

# GitHub Projects

When contributing to the issues on the kanban board, ensure that any changes are logged as comments assigned to the issue. This includes any actions involving:
- Contribution
- Reviewing

**Naming follows similar conventions to the commit messages.**

e.g.,
| Item | Naming Convention | Example |
| --- | --- | --- |
| Backlog Title | `[type]: [title]` | `feat: new page` |
| Commit Message | `#[issue-no] [type]: commit message` | `#1 feat: added textbox` |

## Agile Properties

Each backlog item should contain:

- Priority
- Size
- Estimate
- Iteration
- Start Date, corresponding to when contribution starts
- End Date (can be the planned end date as long as it ultimately corresponds to the final contribution - before being allocated for review)

Additionally, the item should have assigned:

- Label(s)
- Milestones
- Development branch (when contributing to development)

### Priority Guide

| Priority | Explanation |
| --- | --- |
| P0 | Needs to be completed immediately. These items should be taken immediately. |
| P1 | Lower priority. These items should be completed eventually, but are not required within the next 2 weeks. |
| P2 | Nice to haves. Not required but can have if time allows. |

### Estimate Guide

Describes how much time is required to complete a task.

| Estimate | Explanation |
| --- | --- |
| 0 - 1 | Can be completed within 1 day. |
| 1 - 3 | Can be completed within 3 days. |
| 3 - 5 | Can be completed within 1 week. |
| 5 - 8 | Can be completed within 2 weeks. |
| >8 | Undefined time limit. |

## Standard Comment Format

These comments should be present across all backlog items (not including general commenting, e.g., feedback).

> [action] [date] <br/>
> [link (optional)] <br/>
> [comments/changelog (optional)]

Example actions include:
- Completed
- Reviewed